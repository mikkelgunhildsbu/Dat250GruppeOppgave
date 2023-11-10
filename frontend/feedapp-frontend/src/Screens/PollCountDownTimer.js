import React, { useState, useEffect } from 'react';

const PollCountdownTimer = ({ closingTime }) => {
    const [timeRemaining, setTimeRemaining] = useState(calculateTimeRemaining());

    function calculateTimeRemaining() {
        const now = new Date();
        const closingTimeDate = new Date(closingTime);
        const timeDiff = closingTimeDate - now;

        if (timeDiff > 0) {
            const minutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((timeDiff % (1000 * 60)) / 1000);
            return { minutes, seconds };
        } else {
            return { minutes: 0, seconds: 0 };
        }
    }

    useEffect(() => {
        const interval = setInterval(() => {
            setTimeRemaining(calculateTimeRemaining());
        }, 1000);

        return () => clearInterval(interval);
    }, []);

    return (
        <p className="text-wrapper-3">
            {`${timeRemaining.minutes.toString().padStart(2, '0')}:${timeRemaining.seconds.toString().padStart(2, '0')}`}
        </p>
    );
};

export default PollCountdownTimer;
