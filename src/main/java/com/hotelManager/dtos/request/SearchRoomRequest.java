package com.hotelManager.dtos.request;

import com.hotelManager.constants.Constants;
import lombok.Data;

@Data
public class SearchRoomRequest {

    private Long dayCheckIn;

    private Long dayCheckOut;

    private Integer typeRoom;

    private String sortBy = "1";

    private String sortOrder = Constants.SORT_OR_DER_ASC;
}
