public class OrderFactory {

    private final String ABS_ORDER = "abs";
    private final String TYPE_ORDER = "type";
    private final String SIZE_ORDER = "size";

    public Order getOrder(String orderType, boolean isNotReversed) {

        if (orderType == null) return null;

        if (isNotReversed){
            switch (orderType) {
                case (ABS_ORDER):
                    return new RegularOrder(new CompareAbs());
                case (TYPE_ORDER):
                    return new RegularOrder(new CompareType());
                case (SIZE_ORDER):
                    return new RegularOrder(new CompareSize());
            }
        } else {
            /* Use ReverseOrder decorator. */
            switch (orderType){
                case (ABS_ORDER):
                    return new ReverseOrder(new RegularOrder(new CompareAbs()));
                case (TYPE_ORDER):
                    return new ReverseOrder(new RegularOrder(new CompareType()));
                case (SIZE_ORDER):
                    return new ReverseOrder(new RegularOrder(new CompareSize()));
            }
        }

        return null;
    }
}
