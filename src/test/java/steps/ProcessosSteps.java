package steps;

import com.jayway.restassured.internal.RestAssuredResponseImpl;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import definition.Processos;
import org.junit.Assert;
import support.RESTSupport;

import static org.hamcrest.core.IsEqual.equalTo;

public class ProcessosSteps {
    private Response resultado ;
    @Dado("^que tenha preechido o campo (.*) com valor \"([^\"]*)\"$")
    public void que_tenha_preechido_o_campo_vara_com_valor(String arg1, String arg2) throws Throwable {
        Processos.addFields(arg1, arg2);
    }

    @Quando("^executar a criação do novo processo$")
    public void executar_a_criação_do_novo_processo() throws Throwable {
        resultado = RESTSupport.executePost(Processos.getEndPoint(), Processos.getFields());
        Processos.setLastUser(RESTSupport.key("id").toString());
        Processos.clearFields();
    }

    @Então("^a API de proceso deve retornar o status \"([^\"]*)\"$")
    public void a_API_de_proceso_deve_retornar_o_status_com_o_id_do_processo(String arg1) throws Throwable {
        Assert.assertEquals(Integer.parseInt(arg1),resultado.statusCode());
    }

    @Quando("^consultar o processo pelo \"([^\"]*)\" da criação$")
    public void consultar_o_processo_pelo_da_criação(String arg1) throws Throwable {
        resultado = RESTSupport.executeGet(Processos.getEndPoint() + Processos.getLastUser() + ".json");
    }

    @Então("^deve está na (.*) \"([^\"]*)\"$")
    public void deve_está_na_vara(String arg1, String arg2) throws Throwable {
        resultado.then().body(arg1,equalTo(arg2));

    }

    @Quando("^alterar o campo (.*) com valor \"([^\"]*)\"$")
    public void alterar_o_campo_urgente_com_valor(String arg1, String arg2) throws Throwable {
        Processos.addFields(arg1, arg2);
        resultado = RESTSupport.executePut(Processos.getEndPoint() + Processos.getLastUser() + ".json", Processos.getFields());

    }

    @Quando("^excluir o processo$")
    public void excluir_o_processo() throws Throwable {
        resultado = RESTSupport.executeDelete(Processos.getEndPoint() + Processos.getLastUser() + ".json");
    }

    @Então("^consular novamente o processo$")
    public void consular_novamente_o_processo() throws Throwable {
        resultado = RESTSupport.executeGet(Processos.getEndPoint() + Processos.getLastUser() + ".json");
    }


}
