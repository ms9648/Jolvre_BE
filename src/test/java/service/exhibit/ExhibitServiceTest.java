package service.exhibit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.example.jolvre.common.error.exhibition.ExhibitNotFoundException;
import com.example.jolvre.common.service.S3Service;
import com.example.jolvre.exhibition.dto.ExhibitDTO.ExhibitResponse;
import com.example.jolvre.exhibition.dto.ExhibitDTO.ExhibitUploadRequest;
import com.example.jolvre.exhibition.entity.Exhibit;
import com.example.jolvre.exhibition.repository.ExhibitImageRepository;
import com.example.jolvre.exhibition.repository.ExhibitRepository;
import com.example.jolvre.exhibition.service.ExhibitService;
import com.example.jolvre.user.entity.User;
import com.example.jolvre.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExhibitServiceTest {
    @Mock
    ExhibitRepository exhibitRepository;

    @Mock
    S3Service s3Service;

    @Mock
    ExhibitImageRepository imageRepository;
    @Mock
    UserService userService;

    @InjectMocks
    ExhibitService exhibitService;


    @Test
    @DisplayName("Upload Test")
    void uploadTest() {
        User user = new User();

        given(userService.getUserById(any())).willReturn(user);

        ExhibitUploadRequest request = ExhibitUploadRequest.builder()
                .authorWord("a")
                .title("a")
                .price(200)
                .size("a")
                .forSale(true)
                .images(null)
                .introduction("a")
                .thumbnail(null)
                .productionMethod("a").build();

        exhibitService.upload(request, 0L);

        verify(exhibitRepository).save(any());
    }

    @Test
    @DisplayName("Get Exhibit Test")
    void getExhibitTest() {
        Exhibit test = Exhibit.builder().title("test").user(new User()).build();
        given(exhibitRepository.findById(anyLong())).willReturn(Optional.of(test));

        ExhibitResponse response = exhibitService.getExhibit(0L);

        Assertions.assertEquals("test", response.getTitle());
    }

    @Test
    @DisplayName("Get Exhibit Exception Test")
    void getExhibitExceptionTest() {
        given(exhibitRepository.findById(anyLong())).willThrow(new ExhibitNotFoundException());

        Assertions.assertThrows(ExhibitNotFoundException.class, () -> exhibitService.getExhibit(0L));
    }

    @Test
    @DisplayName("Get All Exhibit Test")
    void getAllExhibitTest() {
        User user = new User();
        user.setId(0L);

        List<Exhibit> list = new ArrayList<>();
        list.add(Exhibit.builder().title("test").user(user).build());

        given(exhibitRepository.findAllByUserId(anyLong())).willReturn(list);
        given(userService.getUserById(any())).willReturn(user);

        Assertions.assertEquals("test",
                exhibitService.getAllUserExhibit(0L).getExhibitResponses().get(0).getTitle());
    }
}