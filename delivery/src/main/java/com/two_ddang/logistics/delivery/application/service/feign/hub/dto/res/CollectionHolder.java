package com.two_ddang.logistics.delivery.application.service.feign.hub.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionHolder<T> {

    List<T> content;

}
