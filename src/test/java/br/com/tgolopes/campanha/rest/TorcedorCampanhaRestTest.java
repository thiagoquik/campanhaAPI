package br.com.tgolopes.campanha.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.tgolopes.CampanhaApplication;
import br.com.tgolopes.campanha.mock.TorcedorCampanhaRequestMock;
import br.com.tgolopes.controller.request.TorcedorCampanhaRequest;
import br.com.tgolopes.entity.TorcedorCampanha;
import br.com.tgolopes.exception.TorcedorNaoTemCampanhaAssociada;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {CampanhaApplication.class})
public class TorcedorCampanhaRestTest {

    private static final String BASE_URL = "http://localhost:8081/v1/associacoes/";

    @Autowired
    private TestRestTemplate campanhaRestTemplate;
    
    private HttpEntity<String> httpEntity;

    @Before
    public void init() {
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<>();
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        this.httpEntity = new HttpEntity<>(requestHeaders);
    }

    @Test
    public void deveConsultarTodosTorcedoresCampanhas() {
        ResponseEntity<List<TorcedorCampanha>> responseEntity = campanhaRestTemplate.exchange(BASE_URL,
                HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TorcedorCampanha>>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertFalse(responseEntity.getBody().isEmpty());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
   
    @Test
    public void deveConsultarTorcedorCampanhaPorIdCampanha() {
        String url = BASE_URL.concat("/campanha/1");

        ResponseEntity<List<TorcedorCampanha>> responseEntity = campanhaRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TorcedorCampanha>>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveConsultarTorcedorCampanhaPorEmailTorcedor() {
        String url = BASE_URL.concat("/torcedor");
        ResponseEntity<TorcedorCampanha[]> responseEntity = campanhaRestTemplate.postForEntity(
                url, "thiago@hotmail.com", TorcedorCampanha[].class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp404AoConsultarTorcedorCampanhaPorEmailTorcedorQueNaoExiste() {
        String url = BASE_URL.concat("/torcedor");
        ResponseEntity<TorcedorNaoTemCampanhaAssociada> responseEntity = campanhaRestTemplate.postForEntity(
                url, "joelson@hotmail.com", TorcedorNaoTemCampanhaAssociada.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 404);
    }
    
    @Test
    public void deveIncluirTorcedorCampanhaComSucesso() {
    	TorcedorCampanhaRequest torcedorCampanhaRequest = TorcedorCampanhaRequestMock.getTorcedorCampanhaRequest();

        ResponseEntity<TorcedorCampanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, torcedorCampanhaRequest, TorcedorCampanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirTorcedorCampanhaComEmailNulo() {
        TorcedorCampanhaRequest torcedorCampanhaRequest = TorcedorCampanhaRequestMock.getTorcedorCampanhaRequest();
        torcedorCampanhaRequest.setEmail(null);

        ResponseEntity<TorcedorCampanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, torcedorCampanhaRequest, TorcedorCampanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirTorcedorCampanhaComNomeTimeCoracaoNulo() {
        TorcedorCampanhaRequest torcedorCampanhaRequest = TorcedorCampanhaRequestMock.getTorcedorCampanhaRequest();
        torcedorCampanhaRequest.setNomeTime(null);

        ResponseEntity<TorcedorCampanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, torcedorCampanhaRequest, TorcedorCampanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirTorcedorCampanhaComCodigoCampanhaNulo() {
        TorcedorCampanhaRequest torcedorCampanhaRequest = TorcedorCampanhaRequestMock.getTorcedorCampanhaRequest();
        torcedorCampanhaRequest.setCodigoCampanha(null);

        ResponseEntity<TorcedorCampanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, torcedorCampanhaRequest, TorcedorCampanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
           
    @Test
    public void deveExcluirPorId() throws URISyntaxException {
        String url = BASE_URL.concat("2");

        ResponseEntity<TorcedorCampanha> responseEntity = campanhaRestTemplate.exchange(url, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<TorcedorCampanha>() {});

        Assert.assertNull(responseEntity.getBody());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
}