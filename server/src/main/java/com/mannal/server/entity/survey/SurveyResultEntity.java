package com.mannal.server.entity.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey_result")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@TypeDef(name="jsonb", typeClass = JsonBinaryType.class)
public class SurveyResultEntity implements Serializable {

    private static final long serialVersionUID = 2216904155643294978L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_result_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column
    private UUID surveyCategoryId;

    @NotNull
    @Column
    @Type(type="jsonb")
    private List<Role> role;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @ColumnDefault("Y")
    private String useYn;

    @Column
    private Date regDt;

    @Column
    private Date editDt;

    @Column
    private Date delDt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SurveyResultEntity that = (SurveyResultEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
