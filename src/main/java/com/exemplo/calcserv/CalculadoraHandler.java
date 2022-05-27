package com.exemplo.calcserv;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("calculadora")
public class CalculadoraHandler implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        Long resultado = Long.valueOf(0);
        Long a = (Long) externalTask.getVariable("valorA");
        Long b = (Long) externalTask.getVariable("valorB");
        String operacao = externalTask.getVariable("operacao");

        VariableMap variaveis = Variables.createVariables();
        switch (operacao){
            case "somar":
                resultado = a + b;
                break;
            case "subtrair":
                resultado = a - b;
                break;
            case "multiplicar":
                resultado = a * b;
                break;
            case "dividir":
                resultado = a + b;
                break;
        }
        variaveis.put("resultadoFinal", resultado);
        externalTaskService.complete(externalTask, variaveis);
    }
}
