package dbzoo.api;

import dbzoo.domain.animal.AnimalRepository;
import dbzoo.domain.user.UserRepository;

public interface ZooRepository extends AnimalRepository, UserRepository {
}
