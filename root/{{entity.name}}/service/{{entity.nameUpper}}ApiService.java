package {{path}}.{{entity.name}}.service;

import {{path}}.base.api.reqest.SearchRequest;
import {{path}}.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.mapping.{{entity.nameUpper}}Mapping;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.model.{{entity.nameUpper}}Doc;
import {{path}}.{{entity.name}}.repository.{{entity.nameUpper}}Repository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class {{entity.nameUpper}}ApiService {
//<---------------------------------FINAL------------------------------------------------->
    private final {{entity.nameUpper}}Repository {{entity.name}}Repository;
    private final MongoTemplate mongoTemplate;

//<---------------------------------ПОИСК ПО ID------------------------------------------------->
    public Optional<{{entity.nameUpper}}Doc> findById(ObjectId id){
            return {{entity.name}}Repository.findById(id);
    }

//<---------------------------------СОЗДАНАНИЕ------------------------------------------------->
    public {{entity.nameUpper}}Doc create({{entity.nameUpper}}Request request) throws {{entity.nameUpper}}ExistException {
        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        {{entity.name}}Repository.save({{entity.name}}Doc);
        return {{entity.name}}Doc;
    }

//<---------------------------------СПИСОК БАЗЫ ДАННЫХ------------------------------------------------->
    public SearchResponse<{{entity.nameUpper}}Doc> search(SearchRequest request){

        Criteria criteria = new Criteria();

        if(request.getQuery()!= null && !Objects.equals(request.getQuery(), "")){
            criteria = criteria.orOperator(
//                    TODO: Add criteria
//                    Criteria.where("firstName").regex(request.getQuery(),"i"),
//                    Criteria.where("lastName").regex(request.getQuery(),"i"),
//                    Criteria.where("email").regex(request.getQuery(),"i")
            );
        }

        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, {{entity.nameUpper}}Doc.class);
        query.limit(request.getSize());
        query.skip(request.getSkip());
        List<{{entity.nameUpper}}Doc> {{entity.name}}Docs = mongoTemplate.find(query, {{entity.nameUpper}}Doc.class);
        return  SearchResponse.of({{entity.name}}Docs, count);
    }

//<---------------------------------ОБНОВЛЕНИЕ------------------------------------------------->
    public {{entity.nameUpper}}Doc update({{entity.nameUpper}}Request request) throws {{entity.nameUpper}}NotExistException {
        Optional<{{entity.nameUpper}}Doc> {{entity.name}}DocOptional = {{entity.name}}Repository.findById(request.getId());
        if ({{entity.name}}DocOptional.isEmpty()) throw new {{entity.nameUpper}}NotExistException();
        {{entity.nameUpper}}Doc {{entity.name}}Doc = {{entity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        {{entity.name}}Doc.setId(request.getId());
        {{entity.name}}Repository.save({{entity.name}}Doc);
        return {{entity.name}}Doc;
    }

//<---------------------------------УДАЛЕНИЕ------------------------------------------------->
    public void delete(ObjectId id){
        {{entity.name}}Repository.deleteById(id);
    }
}
