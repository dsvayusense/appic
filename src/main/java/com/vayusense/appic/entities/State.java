package com.vayusense.appic.entities;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "state" )
@Data
public class State {

    @Id
    @GraphQLQuery(name = "id", description = "A object id")
    private String id;
    @GraphQLQuery(name = "co2", description = "A  co2 of state ")
    private Integer co2;
    @GraphQLQuery(name = "ph", description = "A ph of state")
    private Integer ph;
    @GraphQLQuery(name = "startime", description = "A  start time of state")
    private LocalDate startime;
    @GraphQLQuery(name = "endtime", description = "A  end time of state")
    private LocalDate endtime;
    @GraphQLQuery(name = "fermentor", description = "A  fermentor state")
    private String fermentor;



}
