package com.forms.api.forms.domain.repository;

import com.forms.api.forms.domain.Forms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormsRepository extends JpaRepository<Forms, Long> {
}
