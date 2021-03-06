package com.auth0.jwt.impl;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.Payload;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.Instant;
import java.util.*;

import static com.auth0.jwt.impl.JsonNodeClaim.extractClaim;

/**
 * The PayloadImpl class implements the Payload interface.
 */
class PayloadImpl implements Payload {
    private final String issuer;
    private final String subject;
    private final List<String> audience;
    private final Instant expiresAt;
    private final Instant notBefore;
    private final Instant issuedAt;
    private final String jwtId;
    private final Map<String, JsonNode> tree;

    PayloadImpl(String issuer, String subject, List<String> audience, Instant expiresAt, Instant notBefore, Instant issuedAt, String jwtId, Map<String, JsonNode> tree) {
        this.issuer = issuer;
        this.subject = subject;
        this.audience = audience;
        this.expiresAt = expiresAt;
        this.notBefore = notBefore;
        this.issuedAt = issuedAt;
        this.jwtId = jwtId;
        this.tree = Collections.unmodifiableMap(tree == null ? new HashMap<String, JsonNode>() : tree);
    }

    Map<String, JsonNode> getTree() {
        return tree;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public List<String> getAudience() {
        return audience;
    }

    @Override
    public Instant getExpiresAt() {
        return expiresAt;
    }

    @Override
    public Instant getNotBefore() {
        return notBefore;
    }

    @Override
    public Instant getIssuedAt() {
        return issuedAt;
    }

    @Override
    public String getId() {
        return jwtId;
    }

    @Override
    public Claim getClaim(String name) {
        return extractClaim(name, tree);
    }

    @Override
    public Map<String, Claim> getClaims() {
        Map<String, Claim> claims = new HashMap<>();
        for (String name : tree.keySet()) {
            claims.put(name, extractClaim(name, tree));
        }
        return Collections.unmodifiableMap(claims);
    }
}
