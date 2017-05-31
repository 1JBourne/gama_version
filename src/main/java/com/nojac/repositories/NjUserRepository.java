package com.nojac.repositories;

import com.nojac.models.NjUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nickolas on 5/22/17.
 */
@Repository
public interface NjUserRepository extends JpaRepository<NjUser, Long> {

}
