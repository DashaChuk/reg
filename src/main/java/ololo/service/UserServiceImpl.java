package ololo.service;

import ololo.entity.User;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String login) {
        ForSQL sel = new ForSQL();
        sel.createConection();
        ResultSet a = sel.select("Select * from reg.users Where email='"+login+"'");
        List<User> users = new ArrayList<>();
        try {
            while (a.first()){
                User per = new User();
                per.setLogin(a.getString("email"));
                per.setPassword(a.getString("pass"));
                return per;}
        } catch (Exception o) {
            System.out.println("Error" + o.getMessage());
        }
        sel.closeConection();
        return null;
    }

}