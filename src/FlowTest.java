
public class FlowTest {


    public static void main(String[] args) {
        FlowContext flowContext = new FlowContext();
        Service service = new Service() ;
        flowContext.addFlow(service::f);
        flowContext.addFlow(service::g);
        Result result = flowContext.run();
        System.out.println(result);
    }


    static class Service {
        public Result f(FlowContext context) {
            // do some business
            return null;
        }
        public Result g(FlowContext context) {
            // do some business
            // ...
            // exception
            int i = 1 / 0;
            return null;
        }
    }
}
