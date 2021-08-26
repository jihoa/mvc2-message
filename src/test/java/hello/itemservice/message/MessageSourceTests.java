package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

/**
 * @author yjjung
 * @version 0.1.0
 * @since 2021/08/27
 */
@SpringBootTest
class MessageSourceTests {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        final var result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("hello");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        final var result = ms.getMessage("no_code", null, "default message", null);
        assertThat(result).isEqualTo("default message");
    }

    @Test
    void argumentMessage() {
        final var result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("hello Spring");
    }

    @Test
    void defaultLang() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("hello");
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }

    @Test
    void koLang() {
        final var result = ms.getMessage("hello", null, Locale.KOREA);
        assertThat(result).isEqualTo("안녕");
    }

}
