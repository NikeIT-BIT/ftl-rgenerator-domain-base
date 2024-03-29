package {{path}}.{{entity.name}}.controller;

import {{path}}.base.api.reqest.SearchRequest;
import {{path}}.base.api.response.OkResponse;
import {{path}}.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.api.response.{{entity.nameUpper}}Response;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.mapping.{{entity.nameUpper}}Mapping;
import {{path}}.{{entity.name}}.routers.{{entity.nameUpper}}ApiRoutes;
import {{path}}.{{entity.name}}.service.{{entity.nameUpper}}ApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "{{entity.nameUpper}} API")
public class {{entity.nameUpper}}ApiController {
//<---------------------------------FINAL------------------------------------------------->
    private final {{entity.nameUpper}}ApiService {{entity.name}}ApiService;


//<---------------------------------ПОИСК ПО ID------------------------------------------------->
    @GetMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "Find {{entity.name}} by id", notes = "{{entity.nameUpper}} this when you need full info about")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "{{entity.nameUpper}} not found")
    })

    public OkResponse<{{entity.nameUpper}}Response> byId(
    @ApiParam(value = "{{entity.nameUpper}} id") @PathVariable ObjectId id
            ) throws ChangeSetPersister.NotFoundException {
            return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponse().convert(
            {{entity.name}}ApiService.findById(id).orElseThrow(
            ChangeSetPersister.NotFoundException::new)
            ));
            }

//<---------------------------------СОЗДАНАНИЕ------------------------------------------------->
    @PostMapping({{entity.nameUpper}}ApiRoutes.ROOT)
    @ApiOperation(value = "create", notes = "{{entity.nameUpper}} this when you need create and new create {{entity.name}}")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "{{entity.nameUpper}} alreade exist")
    })

    public OkResponse<{{entity.nameUpper}}Response> create(@RequestBody {{entity.nameUpper}}Request request) throws {{entity.nameUpper}}ExistException {
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponse().convert({{entity.name}}ApiService.create(request)));
    }

//<---------------------------------СПИСОК БАЗЫ ДАННЫХ------------------------------------------------->
   @GetMapping({{entity.nameUpper}}ApiRoutes.ROOT)
   @ApiOperation(value = "Search {{entity.name}}", notes = "{{entity.nameUpper}} this when you need find {{entity.name}} by last name first or email")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Success")
   })

    public OkResponse<SearchResponse<{{entity.nameUpper}}Response>> search(
           @ModelAttribute SearchRequest request
           ){
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getSearch().convert(
                {{entity.name}}ApiService.search(request)
        ));

   }

//<---------------------------------ИЗМЕНЕНИЕ ПО ID------------------------------------------------->
   @PutMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
   @ApiOperation(value = "Update {{entity.name}}", notes = "{{entity.nameUpper}} this when you need update {{entity.name}} info")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Success"),
           @ApiResponse(code = 400, message = "{{entity.nameUpper}} ID invalid")
   })

    public OkResponse<{{entity.nameUpper}}Response> {{entity.name}}(
            @ApiParam(value = "{{entity.nameUpper}} id") @PathVariable String id,
            @RequestBody {{entity.nameUpper}}Request {{entity.name}}Request
   ) throws {{entity.nameUpper}}NotExistException {
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponse().convert(
                {{entity.name}}ApiService.update({{entity.name}}Request)
        ));

   }

//<---------------------------------УДАЛЕНИЕ ПО ID------------------------------------------------->
   @DeleteMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
   @ApiOperation(value = "Delete {{entity.name}}", notes = "User this when you need delete {{entity.name}}")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Success")
   })

    public OkResponse<String> deleteUser(
            @ApiParam(value = "User id") @PathVariable ObjectId id
   ){
        {{entity.name}}ApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
   }
}
