package gift.auth.dto;

public record KakaoMessageResponse(
        Integer result_code
) {
    public boolean isSuccessful() {
        return result_code == 0;
    }

}