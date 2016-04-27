package com.proafsolutions.cubatrip.infrastructure.dal.repository;

import com.activeandroid.query.Select;
import com.proafsolutions.cubatrip.domain.model.Product;
import com.proafsolutions.cubatrip.domain.model.RateEnum;
import com.proafsolutions.cubatrip.domain.model.Review;

import java.util.List;

/**
 * Created by alex on 4/17/2016.
 */
public class ReviewRepository extends AbstractRepository<Review> {

    @Override
    public Class<Review> getModelClass() {
        return null;
    }

    public List<Review> retrieveReviews(long productId){
        return new Select()
                .from(Review.class)
                .where("productId = ?", productId)
                .execute();
    }

    public List<Review> retrieveReviewsByRate(long productId, RateEnum rate){
        return new Select()
                .from(Review.class)
                .where("productId = ?", productId)
                .where("rate = ?", rate)
                .execute();
    }

}
