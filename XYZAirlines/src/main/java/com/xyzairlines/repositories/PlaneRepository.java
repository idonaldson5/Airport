package com.xyzairlines.repositories;

import com.xyzairlines.models.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

}
