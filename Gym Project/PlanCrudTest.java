/*
 * Team 4
 * Name: Christian Lopez-Matulessy, Dante Morales, Cesar Trevizo
 * Date: 11/16/2025
 * Course: CS 3331 – Advanced Object-Oriented Programming
 * Instructor: Dr. Bhanukiran Gurijala
 * Project Part 1 - Gym Management System
 * Honesty Statement: We completed this work entirely on our own
 * without any outside sources, including peers,
 * experts, or online sources.
 */

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlanCrudTest {

    @Test
    public void add_and_delete_plan() {
        DataStore.planCount = 0;

        MembershipPlan p = new MembershipPlan();
        p.planName = "Basic";
        p.durationMonths = 3;
        p.price = 99.0;
        DataStore.plans[DataStore.planCount++] = p;

        assertEquals(1, DataStore.planCount);
        assertEquals("Basic", DataStore.plans[0].planName);

        assertTrue(deletePlan("Basic"));
        assertEquals(0, DataStore.planCount);
    }

    static boolean deletePlan(String name) {
        for (int i = 0; i < DataStore.planCount; i++) {
            if (DataStore.plans[i].planName.equals(name)) {
                DataStore.plans[i] = DataStore.plans[DataStore.planCount - 1];
                DataStore.plans[DataStore.planCount - 1] = null;
                DataStore.planCount--;
                return true;
            }
        }
        return false;
    }
}
