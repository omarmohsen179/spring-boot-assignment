package com.example.systemDesign.dto;


import com.example.systemDesign.model.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FolderDto {
    private Long id;
    private String name;
    private String item;

}