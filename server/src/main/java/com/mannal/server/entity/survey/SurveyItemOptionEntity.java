package com.mannal.server.entity.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey_item_option")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveyItemOptionEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_item_option_id", updatable = false, nullable = false)
    private UUID id;

    private String title;

    @NotNull
    private Integer score;

    @NotNull
    private Integer orderNum;

    private String use_yn;
    private Date reg_dt;
    private Date edit_dt;
    private Date del_dt;

    @NotNull
    @ManyToOne
    @JoinColumn(name="survey_item_id")
    private SurveyItemEntity surveyItemEntity;
}
