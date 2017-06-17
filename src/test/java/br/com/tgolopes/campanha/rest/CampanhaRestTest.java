package br.com.tgolopes.campanha.rest;

import java.net.URI;
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
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.tgolopes.CampanhaApplication;
import br.com.tgolopes.campanha.mock.CampanhaRequestMock;
import br.com.tgolopes.controller.request.CampanhaRequest;
import br.com.tgolopes.entity.Campanha;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {CampanhaApplication.class})
public class CampanhaRestTest {

    private static final String BASE_URL = "http://localhost:8081/v1/campanha/";

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
    public void deveConsultarTodasCampanhas() {
        ResponseEntity<List<Campanha>> responseEntity = campanhaRestTemplate.exchange(BASE_URL,
                HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Campanha>>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertFalse(responseEntity.getBody().isEmpty());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
   
    @Test
    public void deveConsultarCampanhaPorId() {
        String url = BASE_URL.concat("1");

        ResponseEntity<Campanha> responseEntity = campanhaRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Campanha>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp404AoConsultarCampanhaPorIdQueNaoExiste() {
        String url = BASE_URL.concat("4651");

        ResponseEntity<Campanha> responseEntity = campanhaRestTemplate.exchange(
                url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Campanha>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 404);
    }

    @Test
    public void deveIncluirCampanhaComSucesso() {
    	CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();

        ResponseEntity<Campanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, campanhaRequest, Campanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirCampanhaComNomeNulo() {
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();
        campanhaRequest.setNomeCampanha(null);

        ResponseEntity<Campanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, campanhaRequest, Campanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirCampanhaComCodigoTimeCoracaoNulo() {
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();
        campanhaRequest.setCodigoTimeCoracao(null);

        ResponseEntity<Campanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, campanhaRequest, Campanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirCampanhaComDataInicioNulo() {
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();
        campanhaRequest.setDataInicio(null);

        ResponseEntity<Campanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, campanhaRequest, Campanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoIncluirCampanhaComDataFimNulo() {
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();
        campanhaRequest.setDataFim(null);

        ResponseEntity<Campanha> responseEntity =
                campanhaRestTemplate.postForEntity(BASE_URL, campanhaRequest, Campanha.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveAtualizarCampanhaComSucesso() throws URISyntaxException {
        String url = BASE_URL.concat("1");
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();

        ResponseEntity<Campanha> responseEntity = campanhaRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(campanhaRequest),
                new ParameterizedTypeReference<Campanha>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarCampanhaComNomeNulo()throws URISyntaxException {
    	String url = BASE_URL.concat("1");
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();
        campanhaRequest.setNomeCampanha(null);

        ResponseEntity<Campanha> responseEntity = campanhaRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(campanhaRequest),
                new ParameterizedTypeReference<Campanha>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarCampanhaComCodigoTimeCoracaoNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();
        campanhaRequest.setCodigoTimeCoracao(null);

        ResponseEntity<Campanha> responseEntity = campanhaRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(campanhaRequest),
                new ParameterizedTypeReference<Campanha>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarCampanhaComDataInicioCoracaoNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();
        campanhaRequest.setDataInicio(null);

        ResponseEntity<Campanha> responseEntity = campanhaRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(campanhaRequest),
                new ParameterizedTypeReference<Campanha>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
    
    @Test
    public void deveRetornarHttp400AoAlterarCampanhaComDataFimCoracaoNulo() throws URISyntaxException{
    	String url = BASE_URL.concat("1");
        CampanhaRequest campanhaRequest = CampanhaRequestMock.getCampanhaRequest();
        campanhaRequest.setDataFim(null);

        ResponseEntity<Campanha> responseEntity = campanhaRestTemplate.exchange(
                url,
                HttpMethod.PUT, RequestEntity.put(new URI(url)).body(campanhaRequest),
                new ParameterizedTypeReference<Campanha>() {});

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 400);
    }
   
    
    @Test
    public void deveExcluirPorId() throws URISyntaxException {
        String url = BASE_URL.concat("2");

        ResponseEntity<Campanha> responseEntity = campanhaRestTemplate.exchange(url, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Campanha>() {});

        Assert.assertNull(responseEntity.getBody());
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
}