query for second question also i impelement it in the spring boot project
String query = "SELECT U.user_id, U.username, TD.training_id, TD.training_date, COUNT(*) AS count " +
                "FROM User U " +
                "INNER JOIN Training_details TD ON U.user_id = TD.user_id " +
                "GROUP BY U.user_id, U.username, TD.training_id, TD.training_date " +
                "HAVING COUNT(*) > 1 " +
                "ORDER BY TD.training_date DESC";
