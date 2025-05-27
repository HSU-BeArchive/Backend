package com.likelion.moamoa.domain.keyword.entity;

import com.likelion.moamoa.domain.folder.entity.Folder;
import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="KEYWORD_ID")
    private Long keywordId;

    @Column(name = "KEYWORD_NAME")
    private String keywordName;

    @Column(name = "KEYWORD_COUNT")
    private Long keywordCount;

    @ManyToOne
    @JoinColumn(name = "FOLDER_ID")
    private Folder folder;


}
