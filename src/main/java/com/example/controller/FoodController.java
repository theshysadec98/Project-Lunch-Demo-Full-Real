package com.example.controller;


import com.example.model.dto.Response.ResponsePage;
import com.example.model.dto.Response.Response;
import com.example.model.dto.Response.ResponseWrapper;
import com.example.model.dto.Requests.FoodRequest;
import com.example.model.entity.Food;
import com.example.service.impl.FoodServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/food")
@Tag(name = "Food")
public class FoodController{

    @Autowired
    private FoodServiceImpl foodService;
    @Operation(description = "The list of food", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Food.class))), responseCode = "200")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Done"),
            @ApiResponse(responseCode = "401", description = "Unconfirmed"),
            @ApiResponse(responseCode = "403", description = "Access is forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<ResponsePage> filter(@RequestParam String name, @RequestParam String taste,
                                               @PageableDefault(size = 3, direction = Sort.Direction.ASC, sort = "name") Pageable page) {
        return new ResponseEntity<>(foodService.findByFilter(name, taste, page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> findById(@PathVariable Long id){
        ResponseWrapper result = new ResponseWrapper(Response.SUCCESS, foodService.findById(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createFood(@RequestBody FoodRequest foodRequest){
        ResponseWrapper result = new ResponseWrapper(Response.SUCCESS, foodService.create(foodRequest));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> editFood(@RequestBody FoodRequest foodRequest){
        ResponseWrapper result = new ResponseWrapper(Response.SUCCESS, foodService.update(foodRequest));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper> deleteFood (@PathVariable long id){
        foodService.remove(id);
        ResponseWrapper result = new ResponseWrapper(Response.SUCCESS, "Delete done.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}