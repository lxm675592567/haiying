package haiying.service.physical.controller;

import haiying.service.physical.domain.User;
import haiying.service.physical.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;

@Validated // <1>
@Controller("/user")
public class UserController {

    protected final haiying.service.physical.service.UserService UserService;

    public UserController(UserService UserService) { // <3>
        this.UserService = UserService;
    }

    @Get("/show/{id}") // <4>
    public User show(Long id) {
        return UserService
                .findById(id)
                .orElse(null); // <5>
    }

//    @Post("/save") // <10>
//    public HttpResponse<Genre> save(@Body @Valid GenreSaveCommand cmd) {
//        Genre genre = UserService.save(cmd.getName());
//
//        return HttpResponse
//                .created(genre)
//                .headers(headers -> headers.location(location(genre.getId())));
//    }

}
