package org.dobrodey.sender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Report {

    private Integer id_report;
    private String userName;
    private String nickName;
    private String task;
    private Timestamp timeOfReport;
    private Timestamp timeOfTrack;
}
