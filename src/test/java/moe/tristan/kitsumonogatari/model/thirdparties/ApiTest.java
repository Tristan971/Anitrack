package moe.tristan.kitsumonogatari.model.thirdparties;

import static java.util.Optional.ofNullable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class ApiTest<T> {

    @Autowired
    private ConfigurableApplicationContext context;

    private RestTemplate restTemplate;

    public Logger logger() {
        return LoggerFactory.getLogger(getClass());
    }

    @Test
    public abstract void testMatches();

    public abstract RequestEntity<T> getRequestEntity();

    public abstract <U> ParameterizedTypeReference<U> getApiType();

    public ResponseEntity<T> fetchInstance() {
        return getRestTemplate().exchange(getRequestEntity(), getApiType());
    }

    public String restTemplateQualifier() {
        return null;
    }

    private void fillRestTemplate() {
        restTemplate = ofNullable(restTemplateQualifier())
                .map(rtq -> getBeanWithQualifier(RestTemplate.class, rtq))
                .orElse(context.getBean(RestTemplate.class));
    }

    public RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            fillRestTemplate();
        }
        return restTemplate;
    }

    private <S> S getBeanWithQualifier(Class<S> beanClass, String qualifier) {
        return BeanFactoryAnnotationUtils.qualifiedBeanOfType(context.getBeanFactory(), beanClass, qualifier);
    }

}
