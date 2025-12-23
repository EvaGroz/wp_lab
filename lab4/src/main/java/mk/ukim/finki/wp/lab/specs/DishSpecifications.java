////za enum
//package mk.ukim.finki.wp.lab.specs;
//import mk.ukim.finki.wp.lab.model.Dish;
//import mk.ukim.finki.wp.lab.model.DishRank;
//import org.springframework.data.jpa.domain.Specification;
//
//public class DishSpecifications {
//    public static Specification<Dish> nameOrCuisineContains(String text) {
//        return (root, query, cb) -> {
//            if (text == null || text.isEmpty()) return null;
//            String pattern = "%" + text.toLowerCase() + "%";
//            return cb.or(
//                    cb.like(cb.lower(root.get("name")), pattern),
//                    cb.like(cb.lower(root.get("cuisine")), pattern)
//            );
//        };
//    }
//
//    public static Specification<Dish> prepTimeLessThan(Integer time) {
//        return (root, query, cb) -> {
//            if (time == null) return null;
//            return cb.lessThan(root.get("preparationTime"), time);
//        };
//    }
//
//    public static Specification<Dish> rankEquals(DishRank rank) {
//        return (root, query, cb) -> {
//            if (rank == null) return null;
//            return cb.equal(root.get("rank"), rank);
//        };
//    }
//
//    public static Specification<Dish> ratingGreaterThan(Double rating) {
//        return (root, query, cb) -> {
//            if (rating == null) return null;
//            return cb.greaterThan(root.get("rating"), rating);
//        };
//    }
//}
