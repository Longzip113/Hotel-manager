package com.hotelManager.dtos.responses;

import com.hotelManager.model.QLKSDetailTypeRoomModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
@Builder
public class QLKSTypeRoomReponse {

    private String id;

    private String nameTypeRoom;

    private String description;

    private Integer price;

    List<QLKSDetailTypeRoomModel> details;

}
