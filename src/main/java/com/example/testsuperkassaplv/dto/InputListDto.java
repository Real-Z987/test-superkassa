package com.example.testsuperkassaplv.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputListDto {
    List<List<String>> test;
}
