package pl.camp.it.rent.database.imp.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.rent.database.IAddressDAO;
import pl.camp.it.rent.database.IOrderDAO;
import pl.camp.it.rent.database.IOrderPositionDAO;
import pl.camp.it.rent.database.IUserDAO;
import pl.camp.it.rent.model.Order;
import pl.camp.it.rent.model.OrderPosition;
import pl.camp.it.rent.model.User;
import pl.camp.it.rent.model.view.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    Connection connection;

    @Autowired
    IAddressDAO addressDAO;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Autowired
    IUserDAO userDAO;



    @Override
    public void addOrder(Order order) {
        try{
            this.addressDAO.addAddress(order.getAddress());

            String sql="INSERT INTO torder (id, address_id, user_id,status) VALUES (NULL,?,?,?)";

            PreparedStatement preparedStatement=this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,order.getAddress().getId());
            preparedStatement.setInt(2,order.getUser().getId());
            preparedStatement.setString(3,order.getStatus().name());

            preparedStatement.executeUpdate();

            ResultSet rs=preparedStatement.getGeneratedKeys();

            rs.next();
            order.setId(rs.getInt(1));

            for(OrderPosition orderPosition: order.getOrderPositions()){
                this.orderPositionDAO.addOrderPosition(orderPosition,order.getId());

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Order> getOrderByUserLogin(String login) {
        List<Order> result = new ArrayList<>();

        Optional<User> userBox = this.userDAO.getUserByLogin(login);

        if (!userBox.isPresent()) {
            return result;

        }
        try {
            String sql = "SELECT * FROM torder WHERE user_id=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, userBox.get().getId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setUser(userBox.get());
                order.setStatus(Order.Status.valueOf(rs.getString("status")));
                order.setId(rs.getInt("id"));
                int addressId=rs.getInt("address_id");
                Optional<Address> addressBox=this.addressDAO.getAddressById(addressId);
                if(addressBox.isPresent()){
                    order.setAddress(addressBox.get());
                }
                //List<OrderPosition> orderPositions= this.orderPositionDAO.getOrderPositionByOrderId(order.getId());
                order.setOrderPositions(this.orderPositionDAO.getOrderPositionByOrderId(order.getId()));
                result.add(order);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    }
