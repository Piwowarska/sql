package pl.camp.it.rent.database.imp.jdbc;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.rent.database.IOrderPositionDAO;
import pl.camp.it.rent.database.ITaleDAO;
import pl.camp.it.rent.model.OrderPosition;
import pl.camp.it.rent.model.Tale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderPositionDAOImpl implements IOrderPositionDAO {

    @Autowired
    Connection connection;

    @Autowired
    ITaleDAO taleDAO;

    @Override
    public void addOrderPosition(OrderPosition orderPosition, int orderId) {
        try{
            String sql="INSERT INTO torderposition (id,order_id,positionquantity) VALUES (NULL,?,?,?)";
            PreparedStatement preparedStatement=this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,orderId);
            preparedStatement.setInt(2,orderPosition.getTale().getId());
            preparedStatement.setInt(3,orderPosition.getPossitionQuantity());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            orderPosition.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<OrderPosition> getOrderPositionByOrderId(int orderId) {
        List<OrderPosition> result =new ArrayList<>();
        try{
            String sql=" SELECT * FROM torderposition WHERE order_id = ?";
            PreparedStatement preparedStatement=this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderId);
            ResultSet rs= preparedStatement.executeQuery();

            while(rs.next()){
                OrderPosition orderPosition=new OrderPosition();
                orderPosition.setId(rs.getInt("id"));
                orderPosition.setPossitionQuantity(rs.getInt("positionquantity"));
                int taleId=rs.getInt("tale_id");
                Optional<Tale> taleBox= this.taleDAO.getTaleById(taleId);
                if(taleBox.isPresent()){
                    orderPosition.setTale(taleBox.get());
                }
                result.add(orderPosition);
            }


        } catch (SQLException | ExecutionControl.NotImplementedException throwables) {
            throwables.printStackTrace();
        }
        return result;

    }
}
