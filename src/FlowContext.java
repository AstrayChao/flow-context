import java.util.ArrayList;
import java.util.List;

public class FlowContext {

    private final List<Function> flows = new ArrayList<>();
    private final ServiceApply service = (context) -> {
        if (flows.isEmpty()) {
            return null;
        }
        Result result = new Result(200, "OK");
        try {
            for (Function flow : flows) {
                Result r = flow.function(context);
                if (r != null) {
                    result = r;
                    break;
                }
            }
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("ERROR");
        }
        return result;
    };

    public void addFlow(Function f) {
        flows.add(f);
    }

    public Result run() {
        return service.function(this);
    }

    @FunctionalInterface
    private interface ServiceApply {
        Result function(FlowContext context);
    }

}


