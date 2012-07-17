/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.reports;

/**
 *
 * @author User
 */
public enum ReportTemplate 
{

    STANDARD, INVOICE, OFFER, DELIVERY;

    public String toString() 
    {
        String output = name().toString(); // Example: NORMAL
        output = output.charAt(0) + output.substring(1).toLowerCase();

        return output; // Output: Normal
    }
}