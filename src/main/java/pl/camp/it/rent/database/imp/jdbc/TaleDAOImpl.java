package pl.camp.it.rent.database.imp.jdbc;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.rent.database.ITaleDAO;
import pl.camp.it.rent.model.Tale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaleDAOImpl implements ITaleDAO {

    @Autowired
    Connection connection;

    @Override
    public List<Tale> getTales() {
        List<Tale> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ttale";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Tale tale = new Tale();

                tale.setId(rs.getInt("id"));
                tale.setTitle(rs.getString("title"));
                tale.setCast(rs.getString("cast"));
                tale.setTag(rs.getString("tag"));
                tale.setQuantity(rs.getInt("quantity"));
                tale.setCena(rs.getInt("cena"));

                result.add(tale);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Tale> getBookByTitle(String title) {
        try{
            String sql="SELECT * FROM ttale WHERE title=?";
            PreparedStatement preparedStatement=this.connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){

                Tale tale=new Tale();
                tale.setId(rs.getInt("id"));
                tale.setTitle(rs.getString("title"));
                tale.setCast(rs.getString("cast"));
                tale.setTag(rs.getString("tag"));
                tale.setQuantity(rs.getInt("quantity"));
                tale.setCena(rs.getInt("cena"));

                return Optional.of(tale);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Tale> getTaleById(int taleId) throws ExecutionControl.NotImplementedException {
        try{
            String sql="SELECT * FROM tbook WHERE id=?";
            PreparedStatement preparedStatement=this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,taleId);

            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                Tale tale=new Tale();
                tale.setId(rs.getInt("id"));
                tale.setTitle(rs.getString("title"));
                tale.setCast(rs.getString("cast"));
                tale.setTag(rs.getString("tag"));
                tale.setQuantity(rs.getInt("quantity"));
                tale.setCena(rs.getInt("cena"));

                return Optional.of(tale);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateTale(Tale tale) {
        try{
            String sql="UPDATE ttale SET title=?,cast=?,tag=?,quantity=?,cena=? WHERE id=?";
            PreparedStatement preparedStatement=this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tale.getTitle());
            preparedStatement.setString(2,tale.getCast());
            preparedStatement.setString(3, tale.getTag());
            preparedStatement.setInt(4,tale.getQuantity());
            preparedStatement.setInt(5,tale.getCena());
            preparedStatement.setInt(6,tale.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
