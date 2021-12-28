package guru.qa.controller;



import guru.qa.domain.LoginInfo;
import guru.qa.domain.Users;
import guru.qa.exception.InvalidUsernameException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class TrueMessageController {

    public Map<String, Users> users = Map.of(
            "Dima", Users.builder().userName("Dima").build(),
            "Olga", Users.builder().userName("Olga").build(),
            "Ivan", Users.builder().userName("Ivan").build()
    );

    @PostMapping("user/login")
    @ApiOperation("авторизация")
    public Users doLogin(@RequestBody LoginInfo loginInfo) {
        if (loginInfo.getUserName().equals("Fedor")) {
            return Users.builder()
                    .loginDate(new Date())
                    .userName(loginInfo.getUserName())
                    .uuid(UUID.randomUUID())
                    .build();
        }
        else  {
            throw new InvalidUsernameException();
        }
    }

//    @PostMapping("user/search")
//    @ApiOperation("Поиск по параметру")
//    public


    @GetMapping("user/search/All")
    @ApiOperation("Получение всех юзеров")
    public List<Users> getAllUsersInfo() {
        List <Users> result = new ArrayList<>();
        for (Map.Entry<String, Users> entry : users.entrySet()) {
            result.add(entry.getValue());
        }


        return users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
/*
 - Регистрация ( Пост с параметрами)

 - Найти друга по ФИО , в ответ получаем его ID ( гет с параметром )

 - Отправка сообщения ( пост с боди)
  - само сообщение
  - id  отправителя
  - is_valid ( true / false)
   в ответе success

 - Получение всех сообщении по своему ID ( гет с параметром)
  - id

 */
