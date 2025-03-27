import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;

import java.io.File;
import java.util.List;
public class RecommendationEngine {
    public static void main(String[] args) throws Exception {
        // Load dataset
        DataModel model = new FileDataModel(new File("data.csv"));

        // Use User-Based Recommendation
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
        GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        // Generate recommendations for user with ID 1
        List<RecommendedItem> recommendations = recommender.recommend(1, 3);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Recommended item: " + recommendation);
        }
    }
}
