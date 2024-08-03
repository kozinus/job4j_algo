package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.List;

public class MostLoadedInterval {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        List<Event> events = new ArrayList<>();
        int[] result = new int[] {-1, -1};
        for (Interval interval : intervals) {
            events.add(new Event(interval.start, true));
            events.add(new Event(interval.end, false));
        }
        events.sort(Event::compareTo);
        int maxLoad = 0;
        int curLoad = 0;
        int oneTimeLoad = 0;
        boolean noShiftFlag = true;
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            oneTimeLoad += event.isStart ? 1 : -1;
            if (i + 1 < events.size() && event.time == events.get(i + 1).time) {
                continue;
            }
            if (oneTimeLoad > 0) {
                curLoad += oneTimeLoad;
                if (curLoad > maxLoad) {
                    result[0] = event.time;
                    maxLoad = curLoad;
                    noShiftFlag = false;
                }
            } else if (oneTimeLoad < 0) {
                if (curLoad == maxLoad && !noShiftFlag) {
                    result[1] = event.time;
                    noShiftFlag = true;
                }
                curLoad += oneTimeLoad;
            }
            oneTimeLoad = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}