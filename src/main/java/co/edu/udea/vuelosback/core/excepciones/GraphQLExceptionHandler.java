package co.edu.udea.vuelosback.core.excepciones;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected graphql.GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof GraphQLException) {
            return graphql.GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .build();
        }
        return null;
    }
}