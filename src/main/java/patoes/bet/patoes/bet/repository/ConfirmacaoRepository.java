package patoes.bet.patoes.bet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patoes.bet.patoes.bet.model.ConfirmacaoModel;

import java.util.Optional;

@Repository
public interface ConfirmacaoRepository extends JpaRepository<ConfirmacaoModel, Long> {

    Optional<ConfirmacaoModel> findByEmail(String email);
}
