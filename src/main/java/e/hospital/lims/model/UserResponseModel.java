package e.hospital.lims.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {
    private String accessToken;
    private String refreshToken;

    public static UserResponseModel from(String accessToken, String refreshToken) {
        return UserResponseModel
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
