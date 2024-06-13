package org.example.chapter1;

public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    public Bag(final Long amount, final Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    public Bag(final Long amount) {
        this(amount, null);
    }

    private void setTicket(final Ticket ticket) {
        this.ticket = ticket;
    }

    private boolean hasTicket() {
        return this.ticket != null;
    }

    private boolean hasInvitation() {
        return this.invitation != null;
    }

    private void minusAmount(Long amount) {
        this.amount -= amount;
    }


    public Long hold(Ticket ticket) {
        if (hasInvitation()) {
            setTicket(ticket);
            return 0L;
        }else{
            minusAmount(ticket.getFee());
            setTicket(ticket);
            return ticket.getFee();
        }
    }

}
