package com.two_ddang.logistics.hub.domain.model;

import com.two_ddang.logistics.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "p_hubs")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("허브")
public class Hub extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Comment("허브 ID")
    private UUID id;

    @Column(nullable = false)
    @Comment("허브 명")
    private String name;

    @Column(nullable = false)
    @Comment("허브 주소")
    private String address;

    @Column(nullable = false)
    @Comment("위도")
    private String latitude;

    @Column(nullable = false)
    @Comment("경도")
    private String longitude;

    @OneToMany(mappedBy = "hub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HubAgent> hubAgents = new ArrayList<>();

    @OneToMany(mappedBy = "hub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HubProduct> hubProducts = new ArrayList<>();

    @OneToMany(mappedBy = "arriveHub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HubRoute> arriveRoutes = new ArrayList<>();

    @OneToMany(mappedBy = "departmentHub", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HubRoute> departmentRoutes = new ArrayList<>();

    private Hub(String name, String address, String latitude, String longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Hub of(String name, String address, String latitude, String longitude) {
        return new Hub(name, address, latitude, longitude);
    }

    public void migration(String address, String latitude, String longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void changeName(String name) {
        this.name = name;
    }

}
