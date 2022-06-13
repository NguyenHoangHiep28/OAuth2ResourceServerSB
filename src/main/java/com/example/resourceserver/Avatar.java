package com.example.resourceserver;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Avatar {
    private Integer id;
    private String url;
    private Integer userId;
}
