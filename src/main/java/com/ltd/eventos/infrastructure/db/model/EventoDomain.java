package com.ltd.eventos.infrastructure.db.model;

import com.ltd.eventos.domain.entities.EventoBusinessRules;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

// Lombok
@Getter
@Setter
@ToString
@NoArgsConstructor

// Spring JPA
@Entity
@Table(name = "evento")
public class EventoDomain {
  @Id
  @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(36)")
  private String evento_id;
  @Column(nullable = false)
  private String evento_nome;
  @Column(nullable = false)
  private String evento_desc;
  @Column(nullable = false)
  private Integer evento_capacidade;
  @Column(nullable = false)
  private LocalDateTime evento_inicio;
  @Column(nullable = false)
  private LocalDateTime evento_fim;
  @Column(nullable = false)
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
  @OneToOne
  @JoinColumn(name = "local_local_id", nullable = false, columnDefinition = "VARCHAR(36)")
  private LocalDomain local_local_id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserDomain user_id;

  public EventoDomain(EventoBusinessRules eventosBusinessRules, LocalDomain localDomain) {
    this.evento_id = eventosBusinessRules.evento_id();
    this.evento_nome = eventosBusinessRules.evento_nome();
    this.evento_desc = eventosBusinessRules.evento_desc();
    this.evento_capacidade = eventosBusinessRules.evento_capacidade();
    this.evento_inicio = eventosBusinessRules.evento_inicio();
    this.evento_fim = eventosBusinessRules.evento_fim();
    this.created_at = eventosBusinessRules.created_at();
    this.local_local_id = localDomain;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    EventoDomain that = (EventoDomain) o;
    return getEvento_id() != null && Objects.equals(getEvento_id(), that.getEvento_id());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
