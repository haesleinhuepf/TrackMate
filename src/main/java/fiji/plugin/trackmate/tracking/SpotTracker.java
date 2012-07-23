package fiji.plugin.trackmate.tracking;

import mpicbg.imglib.algorithm.Algorithm;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import fiji.plugin.trackmate.InfoTextable;
import fiji.plugin.trackmate.Logger;
import fiji.plugin.trackmate.Spot;
import fiji.plugin.trackmate.TrackMateModel;

/**
 * This interface should be used when creating algorithms for linking objects across
 * multiple frames in time-lapse images.
 * <p>
 * A SpotTracker algorithm is simply expected to <b>create</b> a new {@link SimpleWeightedGraph}
 * from the spot collection help in the {@link TrackMateModel} that is given to it. We 
 * use a weighted graph, though the weights themselves are not used for subsequent steps. It is 
 * suggested to use edge weight to report the cost of a link. 
 */
public interface SpotTracker <T extends RealType<T> & NativeType<T>> extends Algorithm, InfoTextable {
	
	/**
	 * Set the {@link TrackMateModel} to operate on. We give the whole model,
	 * for specific trackers might have to access image and other data. However,
	 * a tracker is expected to work on the filtered spot collection returned by
	 * {@link TrackMateModel#getFilteredSpots()} and using the tracker settings
	 * in the settings field.
	 */
	public void setModel(TrackMateModel<T> model);
	
	/**
	 * Set the logger used to echo log messages.
	 */
	void setLogger(Logger logger);
	
	/**
	 * Return the graph containing the link resulting from the process of this tracker.
	 */
	public SimpleWeightedGraph<Spot, DefaultWeightedEdge> getResult();
	
	/**
	 * @return  the name of this spot tracker.
	 */
	@Override
	public String toString();
	
}
