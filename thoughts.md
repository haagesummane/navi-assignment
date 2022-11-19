problem stmt - [codu ai url](https://codu.ai/coding-problem/the%20ledger%20co)
- lending platform for banks
    - I = P*N*R
        I => Interest, P=> Principal, N => Num yrs, R => Rate of Interest
      Total Amt (A) = P+I
      - can pay regular emi
      - can pay lump sum (>emi) that can reduce A and hence num emi
        - at some point A  can be less than emi amt
    
find out
- how much user has paid
- how many emis are remaining

inputs
1. The bank name, borrower name, principal, interest and term.
2. Lump sum payments if any.

outputs
- Given the bank name, borrower name, and EMI number, 
the program should print the total amount paid by the user 
(including the EMI number mentioned) and the remaining number of EMIs.
=> Amount paid so far, and number of EMIs remaining for the user with the bank



-> take a file, read its lines as inputs and print result in commandline/stdout



# working
loan_registry {bank_name: {borrower_name: {loan_id:str, principal:int, rate_of_int:float, n_yrs:float, default_emis:int, lump_sum_payment_emis:[]}}
payments_registry {loan_id:[[latest_emi_no_at_time_of_payment,lump_sum]]}
results_cache {loan_id:{}} # can we use this for preventing a full calculation?
LOAN -> add_keys

# the need to store separate lump_sum_payment_emis and payments_as_kv instead of list
    useful metadata for any kind of analysis
    if emi:payment is stored as kv, then it can be easier to do back filling if needed
    
