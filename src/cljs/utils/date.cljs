(ns utils.date)

(def weekdays ["saturday" "monday" "tuesday" "wednesday" "thursday" "friday" "sunday"])

(defn last-monday [current]
  (->
    current
    (.add "month" -1)
    (.endOf "month")
    (.startOf "week")
    (.add "days" 1)))

(defn first-sunday [current]
  (->
     current
     (.add "month" 1)
     (.startOf "month")
     (.endOf "week")
     (.add "days" 1)))

(defn first-day-of-month [current]
  (let [cloned-date (.clone current)
        start-of-month (.startOf cloned-date "month")
        week-day (.weekday start-of-month)]
    (if (= 1 week-day)
      start-of-month
      (last-monday cloned-date))))


(defn last-day-of-month [current]
  (let [cloned-date (.clone current)
        end-of-month (->
                        cloned-date
                        (.endOf "month"))
        week-day (.weekday end-of-month)]
   (if (= 0 week-day)
     end-of-month
     (first-sunday end-of-month))))