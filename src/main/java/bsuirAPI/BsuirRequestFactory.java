package bsuirAPI;

public class BsuirRequestFactory {
        private enum dataType {XML, JSON}

        private static dataType dType = dataType.XML;

        public static BsuirRequestInterface getRequest(){
            switch (dType){
                case XML:
                    return new BsuirXmlRequests();
                case JSON:
                    return new BsuirJsonRequest();
                default:
                    return null;
        }
    }
}
