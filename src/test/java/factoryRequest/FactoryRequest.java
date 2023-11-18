package factoryRequest;

public class FactoryRequest {
    public static IRequest make (String methodRequest){
        IRequest request;
        switch (methodRequest.toLowerCase()){
            case "post":
                request= new RequestPost();
                break;
            case "put":
                request = new RequestPut();
                break;
            case "delete":
                request = new RequestDelete();
                break;
            default:
                request = new RequestGet();
                break;
        }
        return request;
    }
}
