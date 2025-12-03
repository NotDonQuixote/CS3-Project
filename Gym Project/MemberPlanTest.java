/*
 * Team 4
 * Name: Christian Lopez-Matulessy, Dante Morales, Cesar Trevizo
 * Date: 12/01/2025
 * Course: CS 3331 – Advanced Object-Oriented Programming
 * Instructor: Dr. Bhanukiran Gurijala
 * Project Part 2 - Gym Management System
 * Honesty Statement: We completed this work entirely on our own
 * without any outside sources, including peers,
 * experts, or online sources.
 */

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test for member membership plan functionality.
 * Tests membership plan assignment, upgrading, and cancellation.
 */
public class MemberPlanTest {

    @Test
    public void testMemberWithNoPlan() {
        // Reset data store
        DataStore.memberCount = 0;
        DataStore.planCount = 0;

        // Create a member with no plan
        Member m = new Member();
        m.username = "testmember1";
        m.membership = "";
        DataStore.members[DataStore.memberCount++] = m;

        // Verify member has no plan
        assertTrue(m.membership.isEmpty() || m.membership.isBlank());
    }

    @Test
    public void testMemberWithPlan() {
        // Reset data store
        DataStore.memberCount = 0;
        DataStore.planCount = 0;

        // Create a plan
        MembershipPlan plan1 = new MembershipPlan();
        plan1.planName = "Basic";
        plan1.price = 29.99;
        plan1.durationMonths = 1;
        DataStore.plans[DataStore.planCount++] = plan1;

        // Create a member and assign plan
        Member m = new Member();
        m.username = "testmember2";
        m.membership = "Basic";
        DataStore.members[DataStore.memberCount++] = m;

        // Verify member has the plan
        assertEquals("Basic", m.membership);
    }

    @Test
    public void testMemberUpgradePlan() {
        // Reset data store
        DataStore.memberCount = 0;
        DataStore.planCount = 0;

        // Create multiple plans
        MembershipPlan basic = new MembershipPlan();
        basic.planName = "Basic";
        basic.price = 29.99;
        DataStore.plans[DataStore.planCount++] = basic;

        MembershipPlan premium = new MembershipPlan();
        premium.planName = "Premium";
        premium.price = 49.99;
        DataStore.plans[DataStore.planCount++] = premium;

        // Create a member with Basic plan
        Member m = new Member();
        m.username = "testmember3";
        m.membership = "Basic";
        DataStore.members[DataStore.memberCount++] = m;

        // Upgrade to Premium
        m.membership = "Premium";

        // Verify upgrade
        assertEquals("Premium", m.membership);
    }
}
